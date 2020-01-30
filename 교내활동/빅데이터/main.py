from bs4 import BeautifulSoup
from PIL import Image
import urllib.request
import os

def get(max_count = 1):
    base_url = "http://google.com/" # 이미지 src와 조합하여 다운받을 주소
    url = "https://www.google.com/search?q=rice&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjLk9yLyfDlAhURyYsBHfLEBsMQ_AUIEygC&biw=1064&bih=1065" # 접속할 URL

    count=1
    while count <= max_count:
        print("+-----[%d번 째 이미지]----+" % count)

        html = urllib.request.urlopen(url)
        source = html.read()

        soup = BeautifulSoup(source, "html.parser")

        img = soup.find("img")  # 이미지 태그
        img_src = img.get("src") # 이미지 경로
        img_url = base_url + img_src # 다운로드를 위해 base_url과 합침
        img_name = img_src.replace("/", "") # 이미지 src에서 / 없애기

        urllib.request.urlretrieve(img_url, "./img/" + img_name)

        print("이미지 src:", img_src)
        print("이미지 url:", img_url)
        print("이미지 명:", img_name)
        print("\n")
        count += 1 # 갯수 1 증가
    else:
        print("크롤링 종료!")

def resize_img(name):
    file_list = get_fileName(name)
    for file in file_list:
        print(file)
        image = Image.open('./img/rice/'+file)
        resize_image = image.resize((128,128))
        resize_image.save('./img/rice/'+file)
        
    print('img resized')

def get_fileName(name):
    path = "./img/"+name
    file_list = os.listdir(path)

    print ("file_list: {}".format(file_list))

    return file_list

resize_img('rice')
#resize_img()
