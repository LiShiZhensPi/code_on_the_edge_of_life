//https://www.chiark.greenend.org.uk/~sgtatham/coroutines.html
#include <stdio.h>
#define crBegin           \
    static int state = 0; \
    switch (state)        \
    {                     \
    case 0:
#define crReturn(x)       \
    do                    \
    {                     \
        state = __LINE__; \
        return x;         \
    case __LINE__:;       \
    } while (0)
#define crFinish }

int range(int n)
{
    static int i;
    crBegin
    for (i = 0; i < n; i++)
    {
        crReturn(i);
    }
    crFinish
}

int main()
{
    for (int i = 0; i < 10; i++)
        printf("%d\n", range(10));
}