#include <iostream>
#include <cstdio>
#define MAXFRET 300000
using namespace std;

int main(void)
{
    int N, P, count = 0;
    int top[6] = {-1, -1, -1, -1, -1, -1};
    int strings[6][MAXFRET] = {0};

    scanf("%d %d", &N, &P);

    for(int i=0; i<N; i++) {
        int string, fret;
        scanf("%d %d", &string, &fret);
        string--;
        if (top[string] == -1) {
            strings[string][++top[string]] = fret;
            count++;
        }
        else if (strings[string][top[string]] < fret) {
            strings[string][++top[string]] = fret;
            count++;
        }
        else if (strings[string][top[string]] > fret) {
            while (strings[string][top[string]] > fret) {
                top[string]--;
                count++;
            }
            if (strings[string][top[string]] != fret) {
                strings[string][++top[string]] = fret;
                count++;
            }
        }
        else continue;
    }

    printf("%d\n", count);

    return 0;
}