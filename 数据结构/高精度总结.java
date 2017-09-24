struct BigInteger{  
    int len;
	int s[MAX];
	BigInteger (int num) { *this = num; }  
    BigInteger (const char *num) { *this = num; }  
    BigInteger (){
	    len=1;
	    memset(s,0,sizeof(s));
	}
    BigInteger operator = (const int num){  
        char s[MAX];  
        sprintf(s,"%d",num);  
        *this=s;  
        return *this;  
    }  
    BigInteger operator = (const char *num){  
        for(int i=0;num[i]=='0';num++) ; 
        len=strlen(num);  
        for(int i=0;i<len;i++) s[i]=num[len-i-1]-'0';  
        return *this;  
    }  
    BigInteger operator + (const BigInteger &b){  
        BigInteger c;  
        c.len=0;  
        for(int i=0,carry=0;carry||i<max(len,b.len);i++){  
            int x=carry;  
            if(i<len) x+=s[i];  
            if(i < b.len) x+=b.s[i];  
            c.s[c.len++]=x%10;  
            carry=x/10;  
        }  
        return c;  
    } 
    void clean(){  
        while(len>1&&!s[len-1]) len--;  
    }  
    BigInteger operator * (const BigInteger &b){  
        BigInteger c;  
        c.len=len+b.len;  
        for(int i=0;i<len;i++){  
            for(int j=0;j<b.len;j++){  
                c.s[i+j]+=s[i]*b.s[j];  
            }  
        }  
        for(int i=0;i<c.len;i++){  
            c.s[i+1]+=c.s[i]/10;  
            c.s[i]%=10;  
        }  
        c.clean();  
        return c;  
    } 
    BigInteger operator - (const BigInteger &b){  
        BigInteger c;  
        c.len = 0;  
        for(int i=0,carry=0;i<len;i++){  
            int x=s[i]-carry;  
            if(i<b.len) x-=b.s[i];  
            if(x>=0) carry=0;  
            else{  
                carry=1;  
                x+=10;  
            }  
            c.s[c.len++]=x;  
        }  
        c.clean();  
        return c;  
    }
    BigInteger operator / (const BigInteger &b){  
        BigInteger c,f=0;  
        for(int i=len-1;i>=0;i--){  
            f=f*10;  
            f.s[0]=s[i];  
            while(f>=b){  
                f=f-b;  
                c.s[i]++;  
            }  
        }  
        c.len=len;  
        c.clean();  
        return c;  
    } 
    BigInteger operator % (const BigInteger &b){  
        BigInteger r=*this/b;  
        r=*this-r*b;  
        return r;  
    }
    bool operator < (const BigInteger &b){  
        if(len!=b.len) return len<b.len;  
        for(int i=len-1;i>=0;i--){  
            if(s[i]!=b.s[i]) return s[i]<b.s[i];  
        }  
        return false;  
    }  
    bool operator > (const BigInteger &b){  
        if(len!=b.len) return len>b.len;  
        for(int i=len-1;i>=0;i--){  
            if(s[i]!= b.s[i]) return s[i]>b.s[i];  
        }  
        return false;  
    }  
    bool operator == (const BigInteger &b){  
        return !(*this>b)&&!(*this<b);  
    }  
    bool operator != (const BigInteger &b){  
        return !(*this==b);  
    }  
    bool operator <= (const BigInteger &b){  
        return *this<b||*this==b;  
    }  
    bool operator >= (const BigInteger &b){  
        return *this>b||*this==b;  
    } 
};  
