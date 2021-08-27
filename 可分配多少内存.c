// 查看机器可分配多少内存 在拯救者R7000上，可分配53327MB

#include <stdio.h>
#include <stdlib.h>
int main()
{
    int MB = 0;
    while (malloc(1 << 20))
        ++MB;
    printf("Allocated %d MB total\n", MB);
}