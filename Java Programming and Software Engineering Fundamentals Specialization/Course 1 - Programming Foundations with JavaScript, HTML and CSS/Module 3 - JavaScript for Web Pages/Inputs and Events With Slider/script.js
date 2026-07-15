function doChange2(){
  
  var canvasElement = document.getElementById("canv");
   
  var colorInput = document.getElementById("favcolor");
  
  var color = colorInput.value;
  canvasElement.style.backgroundColor = color;
  
}

function doSquare(){
  
  var canvasElement = document.getElementById("canv");
  
  var context = canvasElement.getContext("2d");
  
  var slider = document.getElementById("sldr");
  
  var len = slider.value;
   context.clearRect(0,0,canvasElement.width,canvasElement.height);
  
  context.fillStyle = "yellow";
  context.fillRect(10,10,len,len);
    context.fillRect(parseInt(len)+20,10,len,len);
  context.fillRect(len*3,10,len,len);
}
  
 


