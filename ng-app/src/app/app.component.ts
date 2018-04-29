import {Component, OnInit} from '@angular/core';
import 'hammerjs';
import {Picture} from "./model/picutre";

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
    {"imageAnimation": "zoom"},
    {"breakpoint": 500, "width": "300px", "height": "300px", "thumbnailsColumns": 3},
    {"breakpoint": 300, "width": "100%", "height": "200px", "thumbnailsColumns": 2}
  ];
  images = [];
  jsons: Picture[] = [];

  ngOnInit(): void {
    const that = this;
    const socket = new SockJS('http://localhost:8090/pictures');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      that.stompClient.subscribe('/topic/reply/', function (greeting) {
        let response = JSON.parse(greeting.body) as Picture[];
        console.log(response[0]);
        that.images = [];
        that.jsons = [];
        for (let i = 0; i < response.length; i++) {
          that.images.push({
            small: response[i].imgUrl,
            medium: response[i].imgUrl,
            large: response[i].imgUrl,
            url: response[i].imgUrl
          });
          that.jsons.push(response[i]);
        }
      });
    }, function (err) {
      console.log('err', err);
    });
  }
}
