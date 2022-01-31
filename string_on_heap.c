/**
 * @file string_on_heap.c
 * @author su yiteng(540726169@qq.com)
 * @brief The string "hello world" is allocated on the head,not in stack.
 * @version 0.1
 * @date 2022-01-31
 *
 * @copyright Copyright (c) 2022
 *
 */
#include <stdio.h>

char *heap_s = "string on heap";

int main()
{
    char *stack_s = "string on stack";
    int stack_i = 4396;

    printf("The adress of {%s} is %x\n",heap_s,heap_s);
    printf("The adress of {%s} is %x\n",stack_s,stack_s);
    printf("The adress of {%d} is %x\n",stack_i,&stack_i);
}