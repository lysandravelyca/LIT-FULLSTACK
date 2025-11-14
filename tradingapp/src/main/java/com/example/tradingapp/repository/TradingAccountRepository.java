package com.example.tradingapp.repository;


  import com.example.tradingapp.model.TradingAccount;
  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.stereotype.Repository;
  import java.util.List;
  
  @Repository
  public interface TradingAccountRepository extends JpaRepository<TradingAccount, Long> {
     
     List<TradingAccount> findByStatus(String status);
     
     List<TradingAccount> findByAccountType(String accountType);
     
     TradingAccount findByAccountName(String accountName);
 }