#include <stdio.h>
#include <stdint.h>
#define MAX_CODE 100
uint32_t stack[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
size_t esp = sizeof(stack) / sizeof(stack[0])-1;

void run(char *code)
{
    for (int i = 0; code[i] != '\0' && i < MAX_CODE; i++)
        switch (code[i])
        {
        case 'a':
            if (esp)
            {
                stack[esp - 1] = stack[esp - 1] + stack[esp];
                esp--;
            }
            break;
        case 's':
            if (esp)
            {
                stack[esp - 1] = stack[esp - 1] - stack[esp];
                esp--;
            }
            break;
        case 'p':
            if (esp)
            {
                esp--;
            }
            break;
        default:
            break;
        }
}

int main()
{
    char code[MAX_CODE];
    uint32_t result;
    printf("This is a simple stack VM,it has thress opcode : a(ADD),s(SUB),p(POP)\n");
    while (1)
    {
        printf("Please write codes:");
        scanf("%s", code);
        printf("Please write result:");
        scanf("%d", &result);
        run(code);
        if (result == stack[esp])
        {
            printf("The password of source code is 20211216\n");
            break;
        }
        else
        {
            printf("Sorry,you are wrong!\n");
        }
    }
}