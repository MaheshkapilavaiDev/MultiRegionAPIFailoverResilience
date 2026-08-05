package com.apifailoverandresilience.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.apifailoverandresilience.dto.RegionRequest;
import com.apifailoverandresilience.dto.RegionResponse;
import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.enums.RegionMode;
import com.apifailoverandresilience.enums.RegionStatus;
import com.apifailoverandresilience.repository.RegionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apifailoverandresilience.util.SecurityUtils;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private AuditLogService auditLogService;

    @InjectMocks
    private RegionService regionService;

    private Region region;
    
    private RegionRequest request;

    @BeforeEach
    void setup() {

        request = new RegionRequest();
        request.setRegionName("Asia");
        request.setRegionCode("AS");
        request.setBaseUrl("http://asia.company.com");
        request.setPriority(1);
        request.setMode("ACTIVE_ACTIVE");
        request.setStatus("ACTIVE");
        request.setHealth("UP");

        region = new Region();
        region.setId(1L);
        region.setRegionName("Asia");
        region.setRegionCode("AS");
        region.setBaseUrl("http://asia.company.com");
        region.setPriority(1);
        region.setMode(RegionMode.ACTIVE_ACTIVE);
        region.setStatus(RegionStatus.ACTIVE);
        region.setHealth("UP");
        region.setCreatedAt(LocalDateTime.now());
        region.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void testCreateRegion() {

        when(regionRepository.existsByRegionCode("AS"))
                .thenReturn(false);

        when(regionRepository.save(any(Region.class)))
                .thenReturn(region);

        try (MockedStatic<SecurityUtils> mocked =
                     mockStatic(SecurityUtils.class)) {

            mocked.when(SecurityUtils::getCurrentUsername)
                    .thenReturn("admin@gmail.com");

            RegionResponse response =
                    regionService.createRegion(request);

            assertNotNull(response);
            assertEquals("Asia", response.getRegionName());

            verify(regionRepository).save(any(Region.class));
            verify(auditLogService).saveAuditLog(
                    anyString(),
                    eq("CREATE_REGION"),
                    eq("REGION"),
                    anyString(),
                    anyString(),
                    eq("SUCCESS"));
        }
    }

    @Test
    void testCreateRegion_WhenRegionCodeExists() {

        when(regionRepository.existsByRegionCode("AS"))
                .thenReturn(true);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> regionService.createRegion(request));

        assertEquals("Region Code already exists", ex.getMessage());
    }

    @Test
    void testGetAllRegions() {

        when(regionRepository.findAll())
                .thenReturn(Arrays.asList(region));

        List<RegionResponse> list =
                regionService.getAllRegions();

        assertEquals(1, list.size());
        assertEquals("Asia", list.get(0).getRegionName());
    }

    @Test
    void testGetRegionById() {

        when(regionRepository.findById(1L))
                .thenReturn(Optional.of(region));

        RegionResponse response =
                regionService.getRegionById(1L);

        assertEquals("Asia", response.getRegionName());
    }

    @Test
    void testGetRegionById_NotFound() {

        when(regionRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> regionService.getRegionById(1L));

        assertEquals("Region not found", ex.getMessage());
    }

    @Test
    void testUpdateRegion() {

        when(regionRepository.findById(1L))
                .thenReturn(Optional.of(region));

        when(regionRepository.save(any(Region.class)))
                .thenReturn(region);

        try (MockedStatic<SecurityUtils> mocked =
                     mockStatic(SecurityUtils.class)) {

            mocked.when(SecurityUtils::getCurrentUsername)
                    .thenReturn("admin@gmail.com");

            RegionResponse response =
                    regionService.updateRegion(1L, request);

            assertEquals("Asia", response.getRegionName());

            verify(regionRepository).save(any(Region.class));
            verify(auditLogService).saveAuditLog(
                    anyString(),
                    eq("UPDATE_REGION"),
                    eq("REGION"),
                    anyString(),
                    anyString(),
                    eq("SUCCESS"));
        }
    }

    @Test
    void testDeleteRegion() {

        when(regionRepository.findById(1L))
                .thenReturn(Optional.of(region));

        try (MockedStatic<SecurityUtils> mocked =
                     mockStatic(SecurityUtils.class)) {

            mocked.when(SecurityUtils::getCurrentUsername)
                    .thenReturn("admin@gmail.com");

            String result =
                    regionService.deleteRegion(1L);

            assertEquals(
                    "Region deleted successfully",
                    result);

            verify(regionRepository).delete(region);

            verify(auditLogService).saveAuditLog(
                    anyString(),
                    eq("DELETE_REGION"),
                    eq("REGION"),
                    anyString(),
                    anyString(),
                    eq("SUCCESS"));
        }
    }

    @Test
    void testUpdateRegionStatus() {

        when(regionRepository.findById(1L))
                .thenReturn(Optional.of(region));

        when(regionRepository.save(any(Region.class)))
                .thenReturn(region);

        RegionResponse response =
                regionService.updateRegionStatus(1L, "INACTIVE");

        assertNotNull(response);

        verify(regionRepository).save(any(Region.class));
    }

    @Test
    void testUpdateRegionStatus_NotFound() {

        when(regionRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> regionService.updateRegionStatus(1L, "ACTIVE"));

        assertEquals("Region not found", ex.getMessage());
    }

}