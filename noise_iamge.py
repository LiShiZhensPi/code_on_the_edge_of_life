# 生成噪音图像，每个像素点随机生成
# 2021/8/20
from PIL import Image
from numpy import *
width = 1080
height = 960

im = zeros([height, width, 3])

for i in range(height):
    for j in range(width):
        for k in range(3):
            # im[i][j][k] = random.random()*255
            im[i,j,k] = random.random()*255

Image.fromarray(im, 'RGB').show()
