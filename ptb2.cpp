#include <bits/stdc++.h>
using namespace std;

typedef complex<double> sp;// complex là kiểu số phức có sẵn trong c++
int main(){
    sp a,b,c,d,x1,x2,K=0;
    cout<<"a= ";cin>>a;
    cout<<"b= ";cin>>b;
    cout<<"c= ";cin>>c;
    if (a==K){
        if(b==K){
            (c==K)?"pt vo nghiem":"pt vo so nghiem";
        }
        else cout<<"x= "<<-b/c;
    }
    else{
        b/=-2;
        d= b*b-a*c;
        cout<<"x1= "<<(b-d)/a;
        cout<<"x2= "<<(b+d)/a;
    }
}