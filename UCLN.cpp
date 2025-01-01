#include <bits/stdc++.h>
using namespace std;

#define input(a){ cout<<"nhap "<<#a<<": "; cin>>a; }

int main(){
    //cout<<_gcd(27,72); return 0;
    unsigned int a,b;
    input(a);
    input(b);
    cout<<"UCLN cua "<<a<<" "<<b<<": ";
    while(a!=b){
        (a>b)?a=a-b:b=b-a;
    }
    cout<<a;
}