//eportfolio Create an image from scratch algorithmically with a pattern

//start with an image
var before = new SimpleImage("pixabayhands.jpg");
print("Before modification pattern");
print(before);


var output = new SimpleImage("pixabayhands.jpg");

//make something here!
for (var pixel of output.values()) {
    var x = Math.random(5);
    var y = pixel.getY()/10;
    var h = output.getHeight();
    if (Math.sin(y) > x && y < h/3) {
    pixel.setBlue(255 - pixel.getBlue());
    }
    else if (Math.sin(y) > x && y >h/3 && y < (h/3)*2) {
        pixel.setRed(255 - pixel.getRed());
    }
    else if (Math.sin(y) > x && y > h/2) {
        pixel.setGreen(255 - pixel.getGreen());
    }
}

for (var pixel of output.values()) {
    var x = Math.random(5);
    var y = pixel.getX()/10;
    var w = output.getWidth();
    if (Math.sin(y) > x) {
        pixel.setBlue(255 - pixel.getBlue());
        pixel.setGreen(255 - pixel.getGreen());
        pixel.setRed(255 - pixel.getRed());
    }
}
print("After modification pattern");
print(output);
