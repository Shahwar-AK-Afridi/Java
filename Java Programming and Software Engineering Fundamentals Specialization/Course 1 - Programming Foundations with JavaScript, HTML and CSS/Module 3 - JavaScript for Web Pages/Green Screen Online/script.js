var fgImage = null;
var bgImage = null;
var canvas;
var canvas2;

function loadForegroundImage(){

  var imageFile = document.getElementById("fgfile");
  fgImage = new SimpleImage(imageFile);
  canvas = document.getElementById("canvas1");
  fgImage.drawTo(canvas);
  alert("Foreground Image Loaded");
}

function loadBackgroundImage(){

  var imageFile = document.getElementById("bgfile");
  bgImage = new SimpleImage(imageFile);
  canvas = document.getElementById("canvas2");
  bgImage.drawTo(canvas);
  alert("Background Image Loaded");
}
function createComposite(){

  var greenThreshold = 240;
  var newImage = new SimpleImage(fgImage.getWidth(),fgImage.getHeight());
  for(var pixel of fgImage.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    
    if(pixel.getGreen()> greenThreshold){
      var bgPixel = bgImage.getPixel(x,y);
      newImage.setPixel(x,y,bgPixel);      
    }
    else{

      newImage.setPixel(x,y,pixel);
    }
    
  }
  return newImage;
}
function doGreenScreen(){

  
  if(fgImage == null || ! fgImage.complete()){

    alert("Forground Image has not been loaded");
  }
    //.complete() allows us to check if our image is completely loaded.
   if(bgImage == null || ! bgImage.complete()){

    alert("Background Image has not been loaded");
  }
  clearCanvas();
  
  var finalImage = createComposite();
  finalImage.drawTo(canvas);
  alert("Green Screen Done");
}

function clearCanvas(){

  var canvas = document.getElementById("canvas1");
  var canvas2 = document.getElementById("canvas2");
  var pen1 = canvas.getContext("2d");
  var pen2 = canvas2.getContext("2d");

  pen1.clearRect(0,0,canvas.width,canvas.height);
  pen2.clearRect(0,0,canvas2.width,canvas2.height);

  alert("Clear Canvas Done");
}