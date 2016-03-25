var image = new SimpleImage("hilton.jpg");
for (var p of image.values()) {
    if (p.getX() < (140 / 3)) {
        p.setRed(255);
    }
    if (p.getX() > ((140 / 3) * 2)) {
        p.setBlue(255);
    }
    if ((p.getX() > (140 / 3)) && (p.getX() < ((140 / 3) * 2))) {
        p.setGreen(255);
    }
    
}
debug(140 / 3);
print(image);
