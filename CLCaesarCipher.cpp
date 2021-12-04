#include <iostream>
#include <string>
using namespace std;

class CLCaesarCipher
{
public:
    CLCaesarCipher(int code, string password) : m_iCode(code), m_sPassword(Cipher(password))
    {
    }

    bool CheckPassword(string password)
    {
        if (m_sPassword.compare(Cipher(password)) == 0)
            return true;
        else
            return false;
    }

private:
    string Cipher(string password)
    {
        for (int i = 0; i < password.length(); i++)
            password[i] = (password[i] - 'a' + m_iCode) % 26 + 'a';
        return password;
    }

private:
    const int m_iCode;
    const string m_sPassword;
};

static const string message = "I mean and I think the reason it works that way is the people who designed C designed it at time when compliers had to be simple and the language had to be kind of neared towards what the output was. So when I read C,I know waht the assembly language will look like and that’s something I care about. ----Linus";

int main()
{
    CLCaesarCipher CaesarCipher(7, "helloworld");
    string password;
    cout << "Please write the password(each one is a~z):" << endl;
    while (cin >> password)
    {
        if (CaesarCipher.CheckPassword(password))
        {
            cout << message << endl;
            return 0;
        }
        else
        {
            cout << "sorry you are wrong" << endl;
        }
    }
}