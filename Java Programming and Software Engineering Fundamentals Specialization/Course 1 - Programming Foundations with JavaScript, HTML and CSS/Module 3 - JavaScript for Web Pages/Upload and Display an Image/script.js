function Upload(){

  var canvas = document.getElementById("canvas");
  var fileInput = document.getElementById("finput");
  var image = new SimpleImage(fileInput);  //Imported Javascript library
  image.drawTo(canvas);
  
}