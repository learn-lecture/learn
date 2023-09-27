package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

// 다형성 때문에 추상 클래스를 씀.
abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {
    private List<T> dataList = new ArrayList<T>();
    private static long index = 0;

    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    // create, update
    @Override
    public T save(T data) {

        if(Objects.isNull(data)) {
            throw new RuntimeException("Data is null");
        }

        var prevData = dataList.stream()
                .filter(it -> {
                    return it.getId().equals(data.getId());
                })
                .findFirst();

        if(prevData.isPresent()) {
            System.out.println(prevData);
            // 기존 데이터가 있는 경우 Update
            // prevData == Optional<UserEntity>
            dataList.remove(prevData.get());
            dataList.add(data);
        }
        else {
            // 없는 경우
            // unique id
            index++;
            data.setId(index);
            dataList.add(data);
        }

        return data;
    }
    // read
    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it -> {
                    return (it.getId().equals(id));
                })
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }

    // delete
    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it -> {
                    return (it.getId().equals(id));
                })
                .findFirst();

        if(deleteEntity.isPresent()) {
            dataList.remove(deleteEntity.get());
        }
    }

}
