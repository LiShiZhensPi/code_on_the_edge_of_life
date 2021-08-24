# 这个例子展示了python的特殊方法
# 为什么在python 会使用abs(num) 而不是 num.abs()
# 为什么使用len(arr)而不是arr.len(),这背后就是python设计哲学的一致性
from math import hypot


class Vector:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def __repr__(self):
        return 'Vector (%r,%r)' % (self.x, self.y)

    def __abs__(self):
        return hypot(self.x, self.y)

    def __bool__(self):
        return bool(abs(self))

    def __add__(self, other):
        x = self.x+other.x
        y = self.y+other.y
        return Vector(x, y)

    def __mul__(self, scalar):
        return Vector(self.x*scalar, self.y*scalar)


a = Vector(1, 2)
print(a)  # 输出Vector (1,2)
print(abs(a))  # 2.23606797749979
print(a*4)  # Vector (4,8)

print(4*a)  # TypeError: unsupported operand type(s) for *: 'int' and 'Vector'
            # 你不可以这样做，因为这样会调用 number的__add__函数，而此函数的参数不可以为Vector
