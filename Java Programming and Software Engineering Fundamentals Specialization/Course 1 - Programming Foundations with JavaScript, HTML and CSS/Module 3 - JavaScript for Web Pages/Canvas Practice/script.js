function doBlue(){
  
    var canElement = document.getElementById("canvas1");
  
  canElement.style.backgroundColor = "blue";
  
  var context = canElement.getContext("2d");
  
  context.fillStyle = "yellow";
  context.fillRect(10,10,60,60);
  context.fillRect(80,10,60,60);
  
  context.fillStyle = "black";
  context.font = "20px Arial";
  context.fillText("Hello", 15, 45);
}

function doRed(){
  
  var canElement = document.getElementById("canvas1");
  
  var context = canElement.getContext("2d");
  
  context.clearRect(0,0,canElement.width,canElement.height);

  canElement.style.backgroundColor = "red";
}


