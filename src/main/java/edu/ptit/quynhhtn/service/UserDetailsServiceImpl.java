package edu.ptit.quynhhtn.service;

import edu.ptit.quynhhtn.dao.CustomerDAO;
import edu.ptit.quynhhtn.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = this.customerDAO.findByPersonInfo_Username(userName).orElse(null);

        if (customer == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("CUSTOMER");
        grantList.add(authority);
        System.out.println(grantList);
        return new User(customer.getPersonInfo().getUsername(),
                customer.getPersonInfo().getPassword(), grantList);
    }

}