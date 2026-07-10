package za.ac.cput.repository;

import za.ac.cput.domain.*;
import java.util.*;

public class AddressRepository implements IAddress {
    private static IAddress repository= null;
    private List<Address> addressList;
    private AddressRepository(){
        addressList =new ArrayList<>();
    }
    public static IAddress getRepository(){
        if(repository==null){
            repository=new AddressRepository();
        }
        return repository;
    }

    @Override
    public Address create(Address address) {
        Boolean sucess = addressList.add(address);
        if(sucess){
            return address;
        }
        return null;
    }

    @Override
    public Address read(String address_id) {
        for(Address address:addressList){
            if(address.getAddress_id().equals(address_id)){
                return address;
            }
        }
        return null;
    }

    @Override
    public Address update(Address address) {
        String id = address.getAddress_id();
        Address oldUser = read(id);

        if(oldUser == null){
            return null;
        }
        boolean sucess = addressList.remove(oldUser);
        if(!sucess){
            return address;
        }
        if (addressList.add(address)) {
            return address;
        }
        return null;
    }

    @Override
    public boolean delete(String address_id) {
        Address addressToDelete = read(address_id);
        if(addressToDelete == null){
            return false;
        }
        return addressList.remove(addressToDelete);
    }

    @Override
    public List<Address> getAll() {
        return List.of();
    }
}
