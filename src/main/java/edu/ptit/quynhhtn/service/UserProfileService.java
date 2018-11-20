package edu.ptit.quynhhtn.service;

import edu.ptit.quynhhtn.dao.CustomerDAO;
import edu.ptit.quynhhtn.dao.PersonDAO;
import edu.ptit.quynhhtn.entity.Customer;
import edu.ptit.quynhhtn.entity.Person;
import edu.ptit.quynhhtn.form.ProfileFrm;
import edu.ptit.quynhhtn.form.RegisterFrm;
import edu.ptit.quynhhtn.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Date;
import java.util.Random;

@Service
public class UserProfileService {
    @Autowired
    PersonDAO personDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    static UserProfileService mInstance;

    public static UserProfileService getInstance() {
        return mInstance;
    }

    @PostConstruct
    public void init(){
        mInstance = this;
    }

    @Transactional
    public void updateProfile(ProfileFrm profileFrm, String userName){
        Customer currentCustomer = customerDAO.findByPersonInfo_Username(userName).orElse(null);
        currentCustomer.setDescription(new Random().nextInt() + "");
        currentCustomer.getPersonInfo().setFullName(profileFrm.getFullName());
        currentCustomer.getPersonInfo().setShortName(profileFrm.getShortName());
        currentCustomer.getPersonInfo().setPhone(profileFrm.getPhone());
        currentCustomer.getPersonInfo().setDob(DateUtils.parseDate(profileFrm.getDob()));
    }

    @Transactional
    public void addNewUser(RegisterFrm registerFrm) {
        Person person = new Person();
        person.setEmail(registerFrm.getEmail());
        person.setUsername(registerFrm.getEmail());
        person.setPassword(passwordEncoder.encode(registerFrm.getPassword()));
        person.setFullName(registerFrm.getFullName());
        person.setShortName(registerFrm.getFullName().split(" ")[registerFrm.getFullName().split(" ").length -1]);
        personDAO.save(person);

        Customer newCustomer = new Customer();
        newCustomer.setJoinDate(new Date());
        newCustomer.setPersonInfo(person);
        newCustomer.setContactNo(person.getPhone());
        customerDAO.save(newCustomer);
    }

    @Transactional
    public Customer currentCustomer(Principal principal){
        return customerDAO.findByPersonInfo_Username(principal.getName()).orElse(null);
    }

    @Transactional
    public Customer currentCustomer(String name){
        return customerDAO.findByPersonInfo_Username(name).orElse(null);
    }
}
