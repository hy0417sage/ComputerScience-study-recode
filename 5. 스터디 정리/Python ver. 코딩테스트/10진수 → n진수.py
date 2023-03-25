def nBase(num, base):
    rev_base = ''

    while num > 0:
        num, mod = divmod(num, base)
        rev_base += str(mod)

    return rev_base[::-1] 
    # 역순인 진수를 뒤집어 줘야 원래 변환 하고자하는 base가 출력
    
print(nBase(45, 3))

# n진수 -> 10진수
# int(num, n)