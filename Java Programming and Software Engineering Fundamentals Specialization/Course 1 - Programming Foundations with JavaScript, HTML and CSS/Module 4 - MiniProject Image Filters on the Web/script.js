var originalImage = null;
var redImage = null;
var grayImage = null;
var rainbowImage = null;
var blurImage = null;
var canvas = null;

function loadImage(){

  var image = document.getElementById("btnUpload");
  canvas = document.getElementById("idCanvas");
  originalImage = new SimpleImage(image);
  redImage = new SimpleImage(image); //check this logic
  grayImage = new SimpleImage(image);
  rainbowImage = new SimpleImage(image);
  blurImage = new SimpleImage(image);
  originalImage.drawTo(canvas);
  alert("Image Loaded");
   
}

function convertToGrayscale(){

  if(isImageUploaded(grayImage)){

    filterGray();
    grayImage.drawTo(canvas);
    
  }
  alert("Image Grayed");
  
}

function convertToRed(){

  if(isImageUploaded(redImage)){

    filterRed();
    redImage.drawTo(canvas);
      
  }
  alert("Image Red");
  
}

function convertToRainbow(){

  if(isImageUploaded(rainbowImage)){

    filterRainbow();
    rainbowImage.drawTo(canvas);
  }
  
}

function convertToBlur(){
  
  if(isImageUploaded(blurImage)){

    filterBlur();
    blurImage.drawTo(canvas);
    
  }
}
function resetToOriginal(){

  if(isImageUploaded(originalImage)){
      
      originalImage.drawTo(canvas);
      grayImage = new SimpleImage(originalImage);
      redImage = new SimpleImage(originalImage);
      rainbowImage = new SimpleImage(originalImage);
      blurImage = new SimpleImage(originalImage);
      alert("Image Reset");
  }
}

function isImageUploaded(image){

 if(image == null || ! image.complete()){

     alert("Image is not loaded");
   return false;
 }
else{

      return true;
  }
  
}

function filterGray(){

    for(var pixel of grayImage.values()){
        
        var grayPixelValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        pixel.setRed(grayPixelValue);
        pixel.setGreen(grayPixelValue);
        pixel.setBlue(grayPixelValue);
    }  
}

function filterRed(){

  for(var pixel of redImage.values()){

        var average =  (pixel.getRed() + pixel.getGreen() +pixel.getBlue())/3;
        
        if(average < 128){

            pixel.setRed(average*2);
            pixel.setGreen(0);
            pixel.setBlue(0);
    
        }
        else{

            pixel.setRed(255);
            pixel.setGreen((2*average)-255);
            pixel.setBlue((2*average)-255);
        }
      }
    
  
}

function filterRainbow(){

  var height = rainbowImage.getHeight();
  var width = rainbowImage.getWidth();
  
  for(var pixel of rainbowImage.values()){
      
    var average = (pixel.getRed() +  pixel.getGreen() +  pixel.getBlue())/3;

    if(pixel.getY() < height/7){
      //red
        if(average < 128){
           
          pixel.setRed(2*average);
          pixel.setGreen(0);
          pixel.setBlue(0);
          
        }else{
    
          pixel.setRed(255);
          pixel.setGreen((2*average)-255);
          pixel.setBlue((2*average)-255);
        }
    }else if(pixel.getY() < height * 2/7){
        //Orange
        if(average < 128){

          pixel.setRed(2*average);
          pixel.setGreen(0.8*average);
          pixel.setBlue(0);
          
        }else{

          pixel.setRed(255);
          pixel.setGreen(1.2*average-51);
          pixel.setBlue((2*average)-255);
          
        }
    } else if(pixel.getY() < height * 3 / 7){
        //Yellow
        if(average < 128){

          pixel.setRed(2*average);
          pixel.setGreen(2*average);
          pixel.setBlue(0);
          
        }else{

          pixel.setRed(255);
          pixel.setGreen(255);
          pixel.setBlue((2*average)-255);
          
        }
    }else if(pixel.getY() < height * 4 / 7){
      //Green
      if(average < 128){

          pixel.setRed(0);
          pixel.setGreen(2*average);
          pixel.setBlue(0);
        
      }else{

        
          pixel.setRed(2*average-255);
          pixel.setGreen(255);
          pixel.setBlue((2*average)-255);
      
      } 
    }else if(pixel.getY() < height * 5 / 7){
       //Blue
       if(average < 128){
          
          pixel.setRed(0);
          pixel.setGreen(0);
          pixel.setBlue(2*average);
        
      }else{

        
          pixel.setRed(2*average-255);
          pixel.setGreen(2*average-255);
          pixel.setBlue(255);
      
      } 
    }else if(pixel.getY() < height * 6 / 7){
      //Indigo
      if(average < 128){
          
          pixel.setRed(0.8*average);
          pixel.setGreen(0);
          pixel.setBlue(2*average);
        
      }else{

        
          pixel.setRed(1.2*average-51);
          pixel.setGreen(2*average-255);
          pixel.setBlue(255);
      
      } 
    }else{

       if(average < 128){
          
          pixel.setRed(1.6*average);
          pixel.setGreen(0);
          pixel.setBlue(1.6*average);
        
      }else{

        
          pixel.setRed(0.4*average+153);
          pixel.setGreen(2*average-255);
          pixel.setBlue(0.4*average+153);
      
      }       
    }       
  } 
}

function filterBlur(){
   
  for(var pixel of originalImage.values()){
    
    var randomSoFar = (Math.trunc(Math.random()*10))/10;
    if(randomSoFar < 0.5){
     
      blurImage.setPixel(pixel.getX(),pixel.getY(),pixel);
    
    }else{

      var otherPixel = findNearByPixel(originalImage,pixel.getX(),pixel.getY(),10);
      blurImage.setPixel(pixel.getX(),pixel.getY(),otherPixel);
    }
  }
  
}
function findNearByPixel(image,x,y,diameter){

    var findX = Math.random() * diameter - diameter / 2;
    var findY = Math.random() * diameter - diameter / 2;
    var newX = ensureInImage(x+findX, image.getWidth());
    var newY = ensureInImage(y+findY, image.getHeight());
    return image.getPixel(newX,newY);
  
}

function ensureInImage(coordinate,size){

  if(coordinate < 0){

    return 0;
  }
  if(coordinate >= size){

    return size - 1;
  }
  return coordinate;
}