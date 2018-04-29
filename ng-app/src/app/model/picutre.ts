export class Picture {
  id: number;
  name: string;
  createdAt: Date;
  imgUrl: string;

  public toString(): string {
    return "[{ id: " + this.id + ", name: " + this.name + ", date: " + this.createdAt + ", imageUrl: " + this.imgUrl + " }]"
  }
}
