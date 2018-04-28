import {Component, OnInit} from '@angular/core';
import 'hammerjs';
import {Picutre} from "./model/picutre";

var SockJS = require('sockjs-client');
var Stomp = require('stompjs');

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit {

  stompClient: any;

  galleryOptions = [
    // {
    //   "thumbnailsColumns": 3,
    //   "thumbnailsRows": 2,
    //   "thumbnailsPercent": 40,
    //   "imagePercent": 60,
    //   "thumbnailMargin": 2,
    //   "thumbnailsMargin": 2
    // },
    // {"breakpoint": 500, "width": "300px", "height": "300px", "thumbnailsColumns": 3},
    // {"breakpoint": 300, "width": "100%", "height": "200px", "thumbnailsColumns": 2}


    {"imageAnimation": "zoom"},
    {"breakpoint": 500, "width": "300px", "height": "300px", "thumbnailsColumns": 3},
    {"breakpoint": 300, "width": "100%", "height": "200px", "thumbnailsColumns": 2}


  ];
  images = [{
    small: "http://picsum.photos/500?image=1",
    medium: "http://picsum.photos/500?image=1",
    large: "http://picsum.photos/500?image=1"
  },
    {
      small: "http://picsum.photos/500?image=2",
      medium: "http://picsum.photos/500?image=2",
      large: "http://picsum.photos/500?image=2"
    },
    {
      small: "http://picsum.photos/500?image=3",
      medium: "http://picsum.photos/500?image=3",
      large: "http://picsum.photos/500?image=3"
    },
    {
      small: "http://picsum.photos/500?image=4",
      medium: "http://picsum.photos/500?image=4",
      large: "http://picsum.photos/500?image=4"
    }];

  ngOnInit(): void {
    const that = this;
    const socket = new SockJS('http://localhost:8090/pictures');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      that.stompClient.subscribe('/topic/reply/', function (greeting) {
        let response = JSON.parse(greeting.body) as Picutre[];
        console.log(response[0]);
        for (let i = 0; i < response.length; i++) {
          that.images.push({small: response[i].imageUrl, medium: response[i].imageUrl, large: response[i].imageUrl})
        }
      });
    }, function (err) {
      console.log('err', err);
    });
  }
}
