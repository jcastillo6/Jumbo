package com.jcastillo.jumbo.sandbox.locator.service;

import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import lombok.*;

/**
 * Helper class
 * @author jorge castillo
 *
 */
@NoArgsConstructor
@Setter
@Getter
public class StoreDistance {
    private Double distance;
    private Store store;

}
