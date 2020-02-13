#include <stdio.h>
#include <stdbool.h>
#define MATRIZ_SIZE 3
#define TAM (MATRIZ_SIZE * MATRIZ_SIZE)

bool findPath(unsigned char matriz[][MATRIZ_SIZE], int xpos, int ypos, bool key)
{
    if(xpos < 0 || xpos >= TAM || ypos < 0 || ypos >= TAM)
        return false;

    if(matriz[xpos][ypos] == '1')
    {
        if(key)
        {
            if(xpos == (TAM - 1) && ypos == (TAM - 1))
                return true;
            return (findPath(matriz, xpos + 1, ypos, false) || findPath(matriz, xpos, ypos + 1, false));
        }

        return false;
    }

    if(xpos == (TAM - 1) && ypos == (TAM - 1))
        return true;
    return (findPath(matriz, xpos + 1, ypos, key) || findPath(matriz, xpos, ypos + 1, key));
}

bool matrizProb(unsigned char matriz[][MATRIZ_SIZE], int xpos, int ypos)
{
    bool key = true;

    if(findPath(matriz, xpos, ypos, key))
        return true;
    return false;
}

int main(void)
{
    unsigned char matriz[MATRIZ_SIZE][MATRIZ_SIZE] = {{'0', '0', '1'}, {'1', '0', '1'}, {'1', '1', '0'}};

    if(matrizProb(matriz, 0, 0))
        puts("Sim");
    else
        puts("Não");

    return 0;
}
