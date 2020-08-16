document.addEventListener('DOMContentLoaded', e => {
    const form = document.forms['compatibility-form'];
    form.addEventListener('submit', async e => {
        e.preventDefault();

        const url = form.action;
        const compatibilityRequest = {};
        const sources = new FormData(form);
        sources.forEach((value, key) => compatibilityRequest[key] = value);

        const compatibilityResponse = await fetch(url, {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(compatibilityRequest)
        });

        const compatibilityResponseBody = await compatibilityResponse.json();
        const results = compatibilityResponseBody.compatibilityResults;

        for (const result of results) {
            const compatibilityStatus = result.compatibilityStatus;
            const evaluationStatus = result.evaluationStatus;
            const incompatibilities = result.incompatibilities;
            const compatibilityName = result.compatibilityName;

            switch (compatibilityStatus) {
                case 'incompatible':
                    const incompatibilityInfo = buildIncompatibilityInfo(incompatibilities);
                    await incompatibleAlert(compatibilityName, incompatibilityInfo.description, incompatibilityInfo.cause);
                    break;
                case 'compatible':
                    await compatibleAlert(compatibilityName, compatibilityStatus);
                    break;
                default:
                    await undefinedCompatibilityAlert(compatibilityName, evaluationStatus);
                    break;
            }
        }
    });
    async function undefinedCompatibilityAlert(compatibilityName, compatibilityStatus) {
        await Swal.fire({
            icon: 'question',
            width: 1000,
            title: compatibilityName,
            text: compatibilityStatus,
        });
    }

    async function incompatibleAlert(compatibilityName, incompatibilityDescription, incompatibilityCause) {
        await Swal.fire({
            icon: 'error',
            width: 1000,
            title: compatibilityName,
            html: incompatibilityDescription,
            footer: `incompatible(${incompatibilityCause})`
        });
    }

    async function compatibleAlert(compatibilityName, evaluationStatus) {
        await Swal.fire({
            icon: 'success',
            width: 1000,
            title: compatibilityName,
            text: evaluationStatus,
        });
    }

    function buildIncompatibilityInfo(incompatibilities) {
        let incompatibilityInfo = {
            cause: '',
            description: ''
        };
        incompatibilities.forEach(inc => {
            const incompatibilityCaseName = inc.incompatibilityCase;
            const incompatibilityDescription = inc.incompatibilityDescription;

            if(incompatibilityInfo.cause.length !== 0) {
                incompatibilityInfo.cause += ', '
            }
            incompatibilityInfo.cause += incompatibilityCaseName;
            incompatibilityInfo.description += `<p>${incompatibilityDescription}</p>`;
        });
        return incompatibilityInfo;
    }
});
