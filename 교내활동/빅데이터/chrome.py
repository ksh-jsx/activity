import os
from selenium import webdriver


def imagescrap(keyword):
    #찾고자 하는 검색어를 url로 만들어 준다.
    
    arr = [keyword ,keyword+' image',keyword+' food',keyword+' img','image '+keyword,'food '+keyword,'img '+keyword,'a '+keyword, 'the '+keyword]
    for i,k in enumerate(arr):
        url = "https://www.google.com/search?q="+k+"&tbm=isch"

        # chrom webdriver 사용하여 브라우저를 가져온다.
        browser = webdriver.Chrome('C:/Users/shkim/Desktop/tttt/chromedriver_win32/chromedriver.exe')
        browser.get(url)


        if not os.path.exists(keyword) and i == 0:
            os.mkdir(keyword)
            os.chdir("./"+keyword)

        for _ in range(500):
            # 가로 = 0, 세로 = 10000 픽셀 스크롤한다.
            browser.execute_script("window.scrollBy(0,10000)")
         
        for idx ,el in enumerate(browser.find_elements_by_class_name("rg_ic")):
            el.screenshot(keyword + str(i)+ "_" + str(idx) + ".png")
            if idx > 50:
                break
                
    browser.close()

imagescrap('greenonion')
