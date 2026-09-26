var image; //Global variable so that both functions can access it
var originalImage;
var grayImage;
function Upload(){

  var originalCanvas = document.getElementById("canvas");
  var fileInput = document.getElementById("finput");
  originalImage = new SimpleImage(fileInput);
  grayImage = new SimpleImage(fileInput);
  originalImage.drawTo(originalCanvas);
  
}

function makeGray(){
    
  for(var pixel of grayImage.values()){
    
      var average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
      pixel.setRed(average);
      pixel.setGreen(average);
      pixel.setBlue(average);
    
}
  var grayCanvas = document.getElementById("canvas2");
  grayImage.drawTo(grayCanvas);
  
}