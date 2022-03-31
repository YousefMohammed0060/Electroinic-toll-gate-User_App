package com.example.finalproject.Profile.Wallets;

public class Wallets {
    String WalletName,UserID;
    int WalletBalance;

    public Wallets() {
    }

    public Wallets(String walletName, String userID, int walletBalance) {
        WalletName = walletName;
        UserID = userID;
        WalletBalance = walletBalance;
    }

    public String getWalletName() {
        return WalletName;
    }

    public void setWalletName(String walletName) {
        WalletName = walletName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getWalletBalance() {
        return WalletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        WalletBalance = walletBalance;
    }
}
