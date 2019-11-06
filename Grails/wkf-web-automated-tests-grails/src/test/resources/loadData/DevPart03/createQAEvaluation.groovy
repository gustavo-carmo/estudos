package loadData.DevPart03

import br.com.careman.domain.Family
import br.com.careman.domain.QualityEvaluation

QualityEvaluation.withTransaction {

    def family = Family.findByName("POS")

    def qualityEvaluation = new QualityEvaluation(status: "ENABLED", description: "QA - Estético", family: family)
    println qualityEvaluation.validate()
    println qualityEvaluation.save()
    println qualityEvaluation.errors

    qualityEvaluation = new QualityEvaluation(status: "ENABLED", description: "QA - Funcional", family: family)
    println qualityEvaluation.validate()
    println qualityEvaluation.save()
    println qualityEvaluation.errors

    qualityEvaluation = new QualityEvaluation(status: "ENABLED", description: "QA - Sistema", family: family)
    println qualityEvaluation.validate()
    println qualityEvaluation.save()
    println qualityEvaluation.errors

}