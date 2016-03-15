/*
 * Copyright 2015-2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hawkular.datamining.forecast;

import static java.lang.Math.log;

/**
 * @author Pavol Loffay
 */
public class InformationCriterionHolder {
    private final double aic;
    private final double aicc;
    private final double bic;

    public InformationCriterionHolder(double sse, double params, int sampleSize) {

        double lik = sampleSize * log(sse);
        aic = lik + 2*params;
        aicc = lik + 2*sampleSize*params / (sampleSize - params - 1);
        bic = lik + log(sampleSize)*params;
    }

    public double informationCriterion(InformationCriterion ic) {
        switch (ic) {
            case aic:
                return aic;
            case bic:
                return bic;
            case aicc:
                return aicc;
        }

        return aicc;
    }

    public double getAic() {
        return aic;
    }

    public double getAicc() {
        return aicc;
    }

    public double getBic() {
        return bic;
    }

    @Override
    public String toString() {
        return "InformationCriterionHolder{" +
                " aic=" + aic +
                ", aicc=" + aicc +
                ", bic=" + bic +
                '}';
    }
}