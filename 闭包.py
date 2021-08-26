#闭包：能访问定义体之外定义的非全局变量的函数
#闭包是一种特殊的对象。
#它由两部分组成。执行上下文(代号A)，以及在该执行上下文中创建的函数（代号B）。
#当B执行时，如果访问了A中变量对象中的值，那么闭包就会产生。
def f1():
    hello='hello world'
    def f2():
        print('hello')
    return f2

f2_global = f1()
f2_global()

#此时打印f2_global变量结果为 <function f1.<locals>.f2 at 0x00000213BD3269D0>
