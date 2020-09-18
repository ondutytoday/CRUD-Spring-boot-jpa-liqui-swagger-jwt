alter table if exists supply
       drop constraint if exists FK_supplier

       GO

drop table if exists supply cascade

GO