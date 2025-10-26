package com.fpt.careermate.services.resume_services.service.impl;

import com.fpt.careermate.services.resume_services.service.dto.request.CertificateRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.CertificateResponse;

public interface CertificateService {
    CertificateResponse addCertificationToResume(CertificateRequest certification);
    void removeCertificationFromResume(int certificationId);
    CertificateResponse updateCertificationInResume(int resumeId, int certificationId, CertificateRequest certification);

}
