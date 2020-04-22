# Multiple tenant by Discriminator 

for Partitioned (Discriminator) Data – the data for each tenant is partitioned by a discriminator value.

Based Framework:
- Spring Data Rest
- Spring JPA
- Hibernate

TO BE VERIFY
- hibernate filter can work (OK)
- @FilterDef can work on baseClass (OK)
- Hibernate Filter can work on Spring Data Rest (OK) 
- CUD operation (OK)
    - Why "put" did not process entity link while "patch" can do ? (put skip association link)
- Hibernate Filter can work on join Query (OK)


_[Go Back](../README.md)_
