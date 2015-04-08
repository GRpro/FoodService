package com.foodservice.security;

import com.foodservice.entities.user.SimpleUser;
import com.foodservice.services.ManagerUserService;
import com.foodservice.services.ShopAdminUserService;
import com.foodservice.services.SimpleUserService;
import com.foodservice.services.UserCommonService;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.ShopAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class CustomUserDetailsService implements UserDetailsService {

    private SimpleUserService simpleUserService;
    private ShopAdminUserService shopAdminUserService;
    private ManagerUserService managerUserService;
    private UserCommonService userCommonService;

    @Autowired
    public void setSimpleUserService(SimpleUserService simpleUserService) {
        this.simpleUserService = simpleUserService;
    }
    @Autowired
    public void setShopAdminUserService(ShopAdminUserService shopAdminUserService) {
        this.shopAdminUserService = shopAdminUserService;
    }
    @Autowired
    public void setManagerUserService(ManagerUserService managerUserService) {
        this.managerUserService = managerUserService;
    }
    @Autowired
    public void setUserCommonService(UserCommonService userCommonService) {
        this.userCommonService = userCommonService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();

        SimpleUser simpleUser = simpleUserService.getByEmail(email);
        if (simpleUser != null) {
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new CustomUserDetails(auths,
                    simpleUser.getId(),
                    simpleUser.getEmail(),
                    simpleUser.getPassword(),
                    simpleUser.getFirstName(),
                    simpleUser.getLastName());
        } else {
            ShopAdminUser shopAdminUser = shopAdminUserService.getByEmail(email);
            if(shopAdminUser != null) {
                auths.add(new SimpleGrantedAuthority("ROLE_SHOP_ADMIN"));
                return new CustomUserDetails(auths,
                        shopAdminUser.getId(),
                        shopAdminUser.getEmail(),
                        shopAdminUser.getPassword(),
                        shopAdminUser.getFirstName(),
                        shopAdminUser.getLastName());
            } else {
                ManagerUser managerUser = managerUserService.getByEmail(email);
                if(managerUser != null) {
                    auths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                    return new CustomUserDetails(auths,
                            managerUser.getId(),
                            managerUser.getEmail(),
                            managerUser.getPassword(),
                            managerUser.getFirstName(),
                            managerUser.getLastName());
                }
            }
        }
        return null;
    }
}