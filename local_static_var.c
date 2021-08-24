/** 此程序用于展示局部static变量的特性，局部static变量位于全局/静态 内存
 * 2021/8/24 苏艺腾
 * 运行结果:
 * var:0
 * var:1
 * var:2
 * var:3
 * var:4
 * var:5
 * var:6
 * var:7
 * var:8
 * var:9
**/

#include <stdio.h>

int flag = 0;

void print_local_static_var()
{
    static int var;
    if (!flag)
    {
        var = 0;
        flag = 1;
    }
    else
    {
        var++;
    }
    printf("var:%d\n", var);
}

int main()
{
    for (int i = 0; i < 10; i++)
        print_local_static_var();
}