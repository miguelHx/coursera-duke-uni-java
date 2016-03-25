//set black function
function setBlack(pixel) {
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(0);
    return pixel;
}

//pixel on edge function that returns true if the pixel's location is within borderWidth of any of the four borders, and thus on the border. Otherwise, return false.
function pixelOnEdge(pixel, image, borderWidth) {
    var x = pixel.getX();
    var y = pixel.getY();
    var width = image.getWidth();
    var height = image.getHeight();
    if (x < borderWidth || y < borderWidth || x >= width - borderWidth || y >= height - borderWidth) {
        pixel = setBlack(pixel);
    }
}
var image = new SimpleImage("usain.jpg");
for (var pixel of image.values()) {
    if (pixelOnEdge(pixel, image, 60)) {
    }
}

function pixelOnHorizontalEdge(pixel, image, borderWidth) {
     var x = pixel.getX();
     var width = image.getWidth();
     if (x < borderWidth || x >= width - borderWidth) {
         return true;
     }
}

function pixelOnVerticalEdge(pixel, image, borderWidth) {
    var y = pixel.getY();
    var height = image.getHeight();
    if (y < borderWidth || y >= height - borderWidth) {
        return true;
    }
}
