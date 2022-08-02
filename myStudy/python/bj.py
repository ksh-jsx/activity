a = list(map(int,input().split()))
num = 1
while 1:
    if (num-a[0])%15==0 and (num-a[1])%28==0 and (num-a[2])%19==0:
        print(num)
        break
    num+=1
