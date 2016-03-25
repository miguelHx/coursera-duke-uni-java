var Image = new SimpleImage("usain.jpg");


function moreRed(px, valueAdded) {
    if (px.getRed() < 245 && (valueAdded + px.getRed() < 255)) {
        var red = px.getRed();
        px.setRed(valueAdded + red);
    }
}

for (var p of Image.values()) {
    moreRed(p, 120);
}
print(Image);
