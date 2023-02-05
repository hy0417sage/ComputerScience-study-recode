import math


def LCM(x, y) : 
    result = (x * y) // math.gcd(x, y)
    return result

# 파이썬 3.9버전 부터 : math.lcm(x, y)