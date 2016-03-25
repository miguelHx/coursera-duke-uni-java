//start with the foreground image you want (fgImage)
var Image = new SimpleImage("smallpanda.png");

print("Before color swap");
print(Image);

function swapRedGreen(px) {
    var green = px.getGreen();
    var red = px.getRed();
    px.setRed(green);
    px.setGreen(red);
}

for (var p of Image.values()) {
    swapRedGreen(p);
}
print("After color swap");
print(Image);
