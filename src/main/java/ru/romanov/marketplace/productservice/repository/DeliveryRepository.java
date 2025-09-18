package ru.romanov.marketplace.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.romanov.marketplace.productservice.entity.delivery.Delivery;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryStatus;
import ru.romanov.marketplace.productservice.entity.delivery.CourierDelivery;
import ru.romanov.marketplace.productservice.entity.delivery.PickupPointDelivery;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findAllByStatus(DeliveryStatus status);

    @Query("SELECT cd FROM CourierDelivery cd WHERE cd.courier.id = :courierId")
    List<CourierDelivery> findByCourierId(@Param("courierId") Long courierId);

    @Query("SELECT pd FROM PickupPointDelivery pd WHERE pd.pickupPointAddress LIKE %:address")
    List<PickupPointDelivery> findAllPickupPointDeliveriesByAddress(@Param("address") String address);

    @Query("SELECT cd FROM CourierDelivery cd WHERE cd.deliveryAddress LIKE %:address")
    List<CourierDelivery> findAllCourierDeliveriesByAddress(@Param("address") String address);

    @Query("SELECT d FROM Delivery d WHERE DATE(d.deliveryTime) = :date")
    List<Delivery> findAllByDeliveryDate(LocalDate date);

}
