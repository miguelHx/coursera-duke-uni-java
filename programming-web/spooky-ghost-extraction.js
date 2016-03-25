//I was trying to figure out how to properly extract the original image
//from the hidden one.  I failed an attempt, and this picture came out...
//(test it using the link from the read me)

function crop(image, width, height) { 
    var n = new SimpleImage(width, height);
        for(var p of image.values()) {
            var x = p.getX();
            var y = p.getY();
            if (x < width && y < height){
            var np = n.getPixel(x,y);
            np.setRed(p.getRed());
            np.setBlue(p.getBlue());
            np.setGreen(p.getGreen());
                }
        }
        return n;
}

function pixchange(pixval){ 
    var x = Math.floor(pixval/16) * 16; //Math.floor rounds the answer down
    return x;
}

function chop2Hide(image) {
    for(var px of image.values()) {
        px.setRed(pixchange(px.getRed()));
        px.setGreen(pixchange(px.getGreen()));
        px.setBlue(pixchange(px.getBlue()));
    }
    return image;
}

function shift(im) {
    var nim = new SimpleImage(im.getWidth(), im.getHeight());
    for(var px of im.values()) {
        var x = px.getX();
        var y = px.getY();
        var npx = nim.getPixel(x,y);
        npx.setRed(Math.floor(px.getRed()/16));
        npx.setGreen(Math.floor(px.getGreen()/16));
        npx.setBlue(Math.floor(px.getBlue()/16));
    }
    return nim;
}
function newpv(p, q) {
    var answer = p + q;
    if (p+q > 255) {
        print("error, answer too big.");
    }
    return answer;
}
function combine(image1, image2) {
    var outimage = new SimpleImage(image1.getWidth(), image1.getHeight());
    for(var px of image1.values()) {
        var x = px.getX();
        var y = px.getY();
        var npx = image2.getPixel(x,y);
        var opx = outimage.getPixel(x,y);
        opx.setRed(newpv(px.getRed(), npx.getRed()));
        opx.setGreen(newpv(px.getGreen(), npx.getGreen()));
        opx.setBlue(newpv(px.getBlue(), npx.getBlue()));
    }
    return outimage;
}
function exnum(num) {
    var value = (num - (Math.floor(num/16))*16)*16;
    return value;
}
function extract(stego) {
    var imExtract = new SimpleImage(stego.getWidth(), stego.getHeight());
    for (var px of stego.values()) {
        var x = px.getX();
        var y = px.getY();
        var npx = imExtract.getPixel(x,y);
        npx.setRed(px.getRed() - exnum(px.getRed()));
        npx.setGreen(px.getGreen() - exnum(px.getGreen()));
        npx.setBlue(px.getBlue() - exnum(px.getBlue()));
    }
    return imExtract;
}

// program - put it all together

var start = new SimpleImage("astrachan.jpg");
var hide = new SimpleImage("duvall.jpg");

print(start);
print(hide);
print("width and height of Astrachan picture");
print(start.getWidth(), start.getHeight());
print("width and height of Duvall picture");
print(hide.getWidth(), hide.getHeight());

var cropWidth = start.getWidth();
if (hide.getWidth() < cropWidth) {
    cropWidth = hide.getWidth();
}
var cropHeight = start.getHeight();
if (hide.getHeight() < cropHeight) {
    cropHeight = hide.getHeight();
}




// hidden code


var start = crop(start, cropWidth, cropHeight);
var hide = crop(hide, cropWidth, cropHeight);
print("Cropped two pictures");


print(start);
print(hide);
print("width and height of Astrachan picture after crop");
print(start.getWidth(), start.getHeight());
print("width and height of Duvall picture after crop");
print(hide.getWidth(), hide.getHeight());

//Hide picture in image - in lower half of each pixel.

/*
print("red at (42,42)");
var sPixel = start.getPixel(42, 42);
print(sPixel.getRed());

start = chop2Hide(start);

print("red at (42, 42) after chop2Hide");
var sPixel = start.getPixel(42,42);
print(sPixel.getRed());

print("red at (42, 42) before shift");
var hPixel = hide.getPixel(42, 42);
print(hPixel.getRed());

hide = shift(hide);

print("red at (42, 42) after shift");
var hPixel = hide.getPixel(42, 42);
print(hPixel.getRed());

var stego = combine(hide, start);
print("Here is the image with the hidden picture");
print(stego);

print("red at (42, 42) after combine");
var hPixel = step.getPixel(42,42);
print(hPixel.getRed());
*/

start = chop2Hide(start);
hide = shift(hide);

var stego = combine(hide, start);
print("Here is the image with the hidden picture");
print(stego);

print(extract(stego));
