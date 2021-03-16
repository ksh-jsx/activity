def is_prime(a):
if a<2 : return False
for i in range(2,a):
if a%i == 0:
return False
return True

while 1:

```
num= int(input())
if num == 0: break
elif num < 4 or num%2 == 1:
    print("Goldbach's conjecture is wrong.")
else:
    prime_list = []
    for i in range(2,num+1):
        if is_prime(i) : prime_list.append(i)

    for i in prime_list:
        if num-i in prime_list:
            print('%d = %d + %d' % (num, i, num-i))
            break
```
