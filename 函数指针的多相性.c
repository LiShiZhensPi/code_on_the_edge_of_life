/**2021/8/27
 * 苏艺腾
 * 令人吃惊的是,下面函数指针的用法都是正确的。
 **/
#include <stdio.h>

void hello(void)
{
    printf("hello\n");
}

void hello_2(void);

int main(void)
{
    void (*hello_p)(void);
    void (*hello_a)(void);
    void (*hello_s)(void);
    hello_p = hello;
    hello_a = &hello_p;
    hello_s = *hello;
    hello();      //hello
    (*hello)();   //hello
    hello_p();    //hello
    (*hello_p)(); //hello
    hello_a();    //hello
    (*hello_a)(); //hello
    hello_s();    //hello
    (*hello_s)(); //hello
}