//duplication program
//To test the code, visit Duke's javascript environment:
//http://www.dukelearntoprogram.com/course1/example/index.php
//NOTE: The syntax may be a bit different than mainstream javascript

var image = new SimpleImage("chapel.png");

    var w = image.getWidth();
    var h = image.getHeight();

function duplicate(image) {
    var outImage = new SimpleImage(2*w,2*h);
    for (var pixel of outImage.values()) {
        var x = pixel.getX();
        var y = pixel.getY();
        var nx = x - Math.floor(x/w)*w;
        var ny = y - Math.floor(y/h)*h;
        var p = image.getPixel(nx, ny);
        pixel.setRed(p.getRed());
        pixel.setGreen(p.getGreen());
        pixel.setBlue(p.getBlue());
    }
    return outImage;
}

out = duplicate(image);
print(out);



