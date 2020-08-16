#include <iostream>
using namespace std;

int main(void)
{
    double a, b;
    cin >> a >> b;
    // cout.precision(12);
    // cout << a / b << endl;
    printf("%.9f\n", a / b);
    return 0;
}