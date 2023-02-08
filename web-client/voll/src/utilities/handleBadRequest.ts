export default function handleBadRequest(code, errors) {
    if (code !== 400) {
        return
    }
    const errorSpans = document.querySelectorAll('.error-message')
    errorSpans.forEach((span) => {
        span.innerHTML = ''
        const input = span.previousSibling as HTMLInputElement
        errors.forEach(formError => {
            if (formError.message.toLowerCase().includes(input.id)) {
                span.innerHTML = formError.message
            }
        });
    });
}