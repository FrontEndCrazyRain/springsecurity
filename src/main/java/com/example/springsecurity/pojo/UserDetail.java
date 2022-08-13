package com.example.springsecurity.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 崔令雨
 * @since 2022/8/11
 */
@Data
@NoArgsConstructor
public class UserDetail implements UserDetails {

    private User user;

    private List<String> jurisdiction;

    public UserDetail(User user, List<String> jurisdiction) {
        this.user = user;
        this.jurisdiction = jurisdiction;
    }

    @JSONField(serialize = false)
    private List<GrantedAuthority> collect;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (CollectionUtils.isEmpty(collect)) {
            collect = jurisdiction.stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
